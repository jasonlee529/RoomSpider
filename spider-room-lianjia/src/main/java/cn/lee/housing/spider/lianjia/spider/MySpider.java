package cn.lee.housing.spider.lianjia.spider;

import org.apache.commons.lang3.SerializationUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by jason on 17-9-14.
 */
public class MySpider extends Spider {

    /**
     * create a spider with pageProcessor.
     *
     * @param pageProcessor pageProcessor
     */
    public MySpider(PageProcessor pageProcessor) {
        super(pageProcessor);
    }

    private void onDownloadSuccess(Request request, Page page) {
        try {
            onSuccess(request);
            if (site.getAcceptStatCode().contains(page.getStatusCode())) {
                pageProcessor.process(page);
                extractAndAddRequests(page, spawnUrl);
                if (!page.getResultItems().isSkip()) {
                    for (Pipeline pipeline : pipelines) {
                        pipeline.process(page.getResultItems(), this);
                    }
                }
            }
        } catch (PageProcessException e) {
            onDownloaderFail(request);
        } finally {
            sleep(site.getSleepTime());
        }
        return;
    }

    private void onDownloaderFail(Request request) {
        if (site.getCycleRetryTimes() == 0) {
            sleep(site.getSleepTime());
        } else {
            // for cycle retry
            doCycleRetry(request);
        }
        onError(request);
    }

    private void doCycleRetry(Request request) {
        Object cycleTriedTimesObject = request.getExtra(Request.CYCLE_TRIED_TIMES);
        if (cycleTriedTimesObject == null) {
            addRequest(SerializationUtils.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 1));
        } else {
            int cycleTriedTimes = (Integer) cycleTriedTimesObject;
            cycleTriedTimes++;
            if (cycleTriedTimes < site.getCycleRetryTimes()) {
                addRequest(SerializationUtils.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, cycleTriedTimes));
            }
        }
        sleep(site.getRetrySleepTime());
    }

}
