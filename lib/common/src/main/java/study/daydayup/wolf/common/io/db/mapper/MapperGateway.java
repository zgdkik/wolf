package study.daydayup.wolf.common.io.db.mapper;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.model.type.string.Tag;
import study.daydayup.wolf.common.util.time.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2020/2/8 6:25 下午
 **/
public class MapperGateway extends AbstractMapper implements Mapper {
    private Row row;
    private List<Mapper> mapperList;

    public MapperGateway() {
        mapperList = new ArrayList<>();
    }

    @Override
    public void map(@NonNull Row row) {
        if (mapperList.isEmpty()) {
            return;
        }

        this.row = row;
        for (Mapper mapper : mapperList) {
            mapper.map(row);
        }
    }

    public MapperGateway toLocalDate(@NonNull String column) {
        return toLocalDate(column, null);
    }

    public MapperGateway toLocalDate(@NonNull String column, @NonNull String newColumn) {
        Mapper mapper = new LocalDateMapper();

        mapper.init(column, newColumn);
        mapperList.add(mapper);

        return this;
    }

    public MapperGateway rename(@NonNull String column, @NonNull String newColumn) {
        Mapper mapper = new RenameMapper();

        mapper.init(column, newColumn);
        mapperList.add(mapper);

        return this;
    }

    public MapperGateway toTag() {
        return toTag(Table.DEFAULT_TAG_COLUMN);
    }

    public MapperGateway toTag(@NonNull String column) {
        Mapper mapper = new TagMapper();

        mapper.init(column, newColumn);
        mapperList.add(mapper);

        return this;
    }

}