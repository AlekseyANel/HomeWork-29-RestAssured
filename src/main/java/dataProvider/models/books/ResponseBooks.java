package dataProvider.models.books;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//вместо нас прописует геттеры и сеттеры
public class ResponseBooks{
	public List<BooksItem> books;
}