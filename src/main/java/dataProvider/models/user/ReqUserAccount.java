package dataProvider.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqUserAccount {
	public String password;
	public String userName;

	public static ReqUserAccount getDefaultRequest() {//подготовленная конструкция для реквестов
		return new ReqUserAccount("1q2w3e4r5T!","AAAEEE");


	}
}