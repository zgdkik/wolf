package study.daydayup.wolf.business.union.task.dts.transformation;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.union.task.dts.Statistics;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.CollectionUtil;
import study.daydayup.wolf.framework.dts.transeformer.Transformation;

/**
 * study.daydayup.wolf.business.union.task.dts.transformation
 *
 * @author Wingle
 * @since 2020/2/8 8:37 下午
 **/
@Component
public class DailyLoanTransformation implements Transformation {

    public Statistics transform(Table table) {
        if (!CollectionUtil.hasValue(table)) {
            return null;
        }



        return null;
    }

    public Table map(Table table) {
        return table;
    }

    public Table filter(Table table) {
        return table;
    }




}
