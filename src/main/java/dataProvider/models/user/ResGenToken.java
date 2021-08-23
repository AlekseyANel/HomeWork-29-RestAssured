package dataProvider.models.user;

import dataProvider.models.books.ReqAddListOfBooks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResGenToken{
	public String result;
	public String expires;
	public String token;
	public String status;
	public static ResGenToken getDefaultRequest() {//подготовленная конструкция для реквестов

		return new ResGenToken("","","","");
	};
}