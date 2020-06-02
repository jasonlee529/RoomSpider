package cn.lee.housing.spider.lianjia.model;

import java.io.Serializable;

/**
 * Created by jason on 17-7-21.
 */
//@MappedSuperclass
public class IdEntity implements Serializable {


    private Long id;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
