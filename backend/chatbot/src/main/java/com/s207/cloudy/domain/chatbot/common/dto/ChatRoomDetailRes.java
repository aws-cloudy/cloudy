package com.s207.cloudy.domain.chatbot.common.dto;

import com.pawsitive.chatgroup.entity.ChatRoom;
import com.pawsitive.usergroup.entity.User;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Builder
public class ChatRoomDetailRes {
    private String chatRoomId;
    private Dog dog;
    private Promise promise;
    private Shelter shelter;
    private Member member;
    private List<ChatRes> chatList;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Dog {
        private int dogNo;
        private String name;
        private String sex;
        private Boolean isNeutralized;
        private int age;
        private String kind;
        private int status;
        @Setter
        private String image;

        public static Dog of(com.pawsitive.doggroup.entity.Dog dog) {
            Dog newDog = Dog.builder()
                .dogNo(dog.getDogNo())
                .name(dog.getName())
                .sex(dog.getSex())
                .isNeutralized(dog.isNeutralized())
                .age(dog.getAge())
                .kind(dog.getKind())
                .status(dog.getStatus().getNo())
                .image(null)
                .build();
            if (!dog.getFiles().isEmpty()) {
                newDog.setImage(dog.getFiles().get(0).getFile());
            }
            return newDog;
        }
    }


    @Getter
    @NoArgsConstructor
    @Setter
    public static class Promise {
        private Boolean isAccepted;
        private String date;
        private String time;

        public static Promise of(ChatRoom chatRoom) {
            Promise promise = new Promise();
            promise.setIsAccepted(chatRoom.getIsPromiseAccepted());
            if (chatRoom.getPromiseCreatedAt() != null) {
                promise.setDate(
                    chatRoom.getPromiseCreatedAt()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                promise.setTime(
                    chatRoom.getPromiseCreatedAt().format(DateTimeFormatter.ofPattern("HH:mm")));
            }
            return promise;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Shelter {
        private String name;
        private int userNo;
        private String image;

        public static Shelter of(User shelter) {
            return new Shelter(shelter.getName(), shelter.getUserNo(), shelter.getImage());
        }
    }

    @AllArgsConstructor
    @Getter
    public static class Member {
        private String name;
        private int userNo;
        private String image;

        public static Member of(com.pawsitive.usergroup.entity.Member member) {
            return new Member(member.getUser().getName(), member.getUserNo(),
                member.getUser().getImage());
        }

    }
}
