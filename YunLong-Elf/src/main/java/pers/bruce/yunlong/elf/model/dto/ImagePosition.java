package pers.bruce.yunlong.elf.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 图片定位
 * </p>
 *
 * @author BruceLee
 * @since 2020/12/28
 */
@Data
@NoArgsConstructor
public class ImagePosition {

    private static final ImagePosition NOT_FIND = new ImagePosition(-1, -1);

    /**
     * x坐标
     */
    private Integer x;

    /**
     * y坐标
     */
    private Integer y;

    public ImagePosition(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public static ImagePosition notFind() {
        return NOT_FIND;
    }
}
