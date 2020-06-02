package cn.lee.housing.spider.lianjia.model.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskJob {
    private Long id;

    private String name;

    private String taskId;

    private String createDate = new DateTime().toString("yyyy-MM-dd HH:mm:ss");

    private Long pageCount;

}