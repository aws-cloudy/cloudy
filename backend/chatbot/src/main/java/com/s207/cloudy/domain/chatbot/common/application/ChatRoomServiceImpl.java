package com.s207.cloudy.domain.chatbot.common.application;


import com.s207.cloudy.domain.chatbot.common.dto.ChatRes;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomCreateReq;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomDetailRes;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomListRes;
import com.s207.cloudy.domain.chatbot.common.dto.ChatRoomRes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;


    /**
     * 채팅방을 생성
     *
     * @return 생성한 채팅방
     */
    @Override
    @Transactional
    public ChatRoomRes createChatRoom(ChatRoomCreateReq req, Authentication authentication) {
        ChatRoom room = new ChatRoom();

        int userNo = req.getUserNo();
        int dogNo = req.getDogNo();


        if (existChatRoom.isPresent()) {
            return ChatGroupTransfer.entityToDto(existChatRoom.get());
        }


        room.setId(getRandomRoomNo());
        ChatRoom chatRoom = chatRoomRepository.save(room);

        return ChatGroupTransfer.entityToDto(chatRoom);
    }

    private String getRandomRoomNo() {
        String roomNo = UUID.randomUUID().toString();
        roomNo = roomNo.replace("-", "");
        roomNo = roomNo.substring(0, 16);
        return roomNo;
    }

    @Override
    public ChatRoom getChatRoomEntityByChatRoomNo(int chatRoomNo) {
        return chatRoomRepository.findByChatRoomNo(chatRoomNo)
            .orElseThrow(ChatRoomNotFoundException::new);
    }

    /**
     * 채팅방 고유번호로 채팅 이력을 조회
     *
     * @param chatRoomNo 조회할 채팅방의 고유 번호
     * @return
     */
    @Override
    public List<ChatRes> getChatHistoryByChatRoomNo(int chatRoomNo) {
        return chatRoomRepository.getChatHistoryByChatRoomNo(chatRoomNo);
    }

    /**
     * 최근에 생성된 순으로 채팅방 전체 조회
     *
     * @return 최근에 생성된 순으로 조회한 채팅방 목록
     */
    @Override
    public List<ChatRoomListRes> getChatRoomList(String type, int value) {

        List<ChatRoomListRes> chatRooms = null;

        if ("dogNo".equals(type)) {
            chatRooms = chatRoomRepository.getChatRoomListByDogNo(value);
        } else if ("userNo".equals(type)) {
            chatRooms = chatRoomRepository.getChatRoomListByUserNo(value);
        } else {
            throw new InvalidRequestDataException("유기견 번호나 회원 번호로만 조회 가능합니다.");
        }
        for (ChatRoomListRes chatRoom : chatRooms) {
            User user = userService.getUserByUserNo(chatRoom.getMemberNo());
            chatRoom.setMemberName(user.getName());
            chatRoom.setMemberProfileImage(user.getImage());
        }
        setLastChat(chatRooms);

        return chatRooms;
    }

    private void setLastChat(List<ChatRoomListRes> chatRooms) {
        for (ChatRoomListRes chatRoom : chatRooms) {
            LastChatTmp lastChat = chatRoomRepository.getLastChat(chatRoom.getChatRoomNo());
            if (lastChat != null) {
                if (lastChat.getCreatedDate() == null) {
                    continue;
                }
                if (lastChat.getCreatedDate().toLocalDate().isEqual(LocalDate.now())) {
                    chatRoom.setLastChat(new ChatRoomListRes.LastChat(lastChat.getMessage(),
                        LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                } else {
                    chatRoom.setLastChat(new ChatRoomListRes.LastChat(lastChat.getMessage(),
                        lastChat.getCreatedDate().format(DateTimeFormatter.ofPattern("HH:mm"))));
                }
            }
        }
    }


    @Override
    public ChatRoomDetailRes getChatRoomDetail(int chatRoomNo) {
        ChatRoom chatRoom = getChatRoomEntityByChatRoomNo(chatRoomNo);
        Member member = userService.getMemberByUserNo(chatRoom.getUserNo());
        return ChatRoomDetailRes.builder().chatRoomId(chatRoom.getId())
            .shelter(ChatRoomDetailRes.Shelter.of(shelter))
            .member(ChatRoomDetailRes.Member.of(member)).dog(ChatRoomDetailRes.Dog.of(dog))
            .chatList(getChatHistoryByChatRoomNo(chatRoomNo)).build();
    }


}
