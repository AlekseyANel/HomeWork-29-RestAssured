package dataProvider.models.books;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//вместо нас прописует геттеры и сеттеры
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBooks{
	public List<BooksItem> books;
}