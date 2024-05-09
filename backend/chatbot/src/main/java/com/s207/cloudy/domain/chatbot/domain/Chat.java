import java.util.UUID;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Chat {
    private UUID id;
    private String name;
    private String lastName;

    @DynamoDbPartitionKey
    public UUID getId() {
        return id;
    }

    ...
}

