package dataProvider.models.user;

import java.util.List;

import dataProvider.models.books.BooksItem;
import dataProvider.models.books.ReqAddListOfBooks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//вместо нас прописует геттеры и сеттеры
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResUserAccount{//класс UserAccount для хранения информации, связанной с учетной записью пользователя:
	// такой как книги, связанные с пользователем, userID и userName.
	public List<BooksItem> books;
	public String userId;
	public String username;
/*	public static ResUserAccount getDefaultRequest() {//подготовленная конструкция для реквестов
		return new ResUserAccount("","","");
	};*/
}