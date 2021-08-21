package dataProvider.models.user;

import dataProvider.models.books.ReqAddListOfBooks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqAuthUser {
	public String password;
	public String userName;

	public static ReqAuthUser getDefaultRequest() {//подготовленная конструкция для реквестов
		new ReqAuthUser("1q2w3e4r5T!","AAA");

		return null;
	}
}