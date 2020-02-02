package cn.lee.housing.spider.lianjia.model.task;

import cn.lee.housing.spider.lianjia.model.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "infisa_task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task extends IdEntity {

    private String name;

    private String cron;

    private String trigger;

}
