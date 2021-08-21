package dataProvider.models.books;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*{
  "userId": "string",
  "collectionOfIsbns": [
    {
      "isbn": "string"
    }
  ]
}*/
/*@Data
/*@AllArgsConstructor
@NoArgsConstructor*/
public class CollectionOfIsbnsItem{
	public static String isbn;


	public static String getIsbn(){
		return isbn;
	}

}
