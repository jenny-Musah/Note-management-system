package note.data.models;

import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@Document
public class Note {
    @Id
    private String id;

    private String userId;
   private String title;
   private String body;
   private LocalDate date = LocalDate.now();

}
