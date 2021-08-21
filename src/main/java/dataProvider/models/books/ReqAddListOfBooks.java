package dataProvider.models.books;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*{
  "userId": "string",
  "collectionOfIsbns": [
    {
      "isbn": "string"
    }
  ]
}*/
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class ReqAddListOfBooks {
	public String userId;
	//public List<CollectionOfIsbnsItem> collectionOfIsbns;



	//	collectionOfIsbns = new ArrayList<CollectionOfIsbnsItem>();
	public static ReqAddListOfBooks getDefaultRequest() {//подготовленная конструкция для реквестов
		new ReqAddListOfBooks("String");
		return null;

	};
}
//"collectionOfIsbns[0]={\"isbn\": \"string\"}"
/*"CollectionOfIsbnsItem.getIsbn()","String"*/
/*	public ReqAddListOfBooks(String string, String collectionOfIsbns) {
		this.string=string;
		this.collectionOfIsbns = collectionOfIsbns;
	}*/