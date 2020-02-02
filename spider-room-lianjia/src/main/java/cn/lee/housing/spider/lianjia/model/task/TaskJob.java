package cn.lee.housing.spider.lianjia.model.task;

import cn.lee.housing.spider.lianjia.model.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lee_task_job")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskJob extends IdEntity {

    private String name;

    private String taskId;

    private String createDate = new DateTime().toString("yyyy-MM-dd HH:mm:ss");

    private Long pageCount;


}
