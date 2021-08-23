package dataProvider.models.books;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONArray;

/*{
  "userId": "string",
  "collectionOfIsbns": [  {   "isbn": "string"   } ]
}*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqAddListOfBooks {
	public String userId;
//	public List<CollectionOfIsbnsItem> collectionOfIsbns;

	public static ReqAddListOfBooks getDefaultRequest() {//подготовленная конструкция для реквестов
		return new ReqAddListOfBooks("1dd13dec-4558-4423-8739-f4d8c0432fcc" /*"[\n" +
				"    {\n" +
				"      \"isbn\": \"string\"\n" +
				"    }\n" +
				"  ]"*/);
	};
}
