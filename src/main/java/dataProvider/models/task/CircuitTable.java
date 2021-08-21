package dataProvider.models.task;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//без геттеров и сеттеров, при компиляции
@AllArgsConstructor
@NoArgsConstructor
public class CircuitTable{
	private List<CircuitsItem> circuits;
	private String season;
}