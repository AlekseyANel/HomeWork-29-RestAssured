package dataProvider.models.books;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*{
  "userId": "string",
  "collectionOfIsbns": [  {   "isbn": "string"   } ]
}*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionOfIsbnsItem{// класс описывает каждый элемент листа "collectionOfIsbns"
	public static String isbn;
	}

