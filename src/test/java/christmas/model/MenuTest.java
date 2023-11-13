package christmas.model;

import static christmas.model.Menu.BARBECUE_RIBS;
import static christmas.model.Menu.CAESAR_SALAD;
import static christmas.model.Menu.ICE_CREAM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuTest {

    @DisplayName("메뉴 이름을 통해서 메뉴 객체를 찾는다.")
    @Test
    void findMenuTest() {
        int numberOfCases = 3;
        String[] strings = {"바비큐립", "시저샐러드", "아이스크림"};
        Menu[] menus = {BARBECUE_RIBS, CAESAR_SALAD, ICE_CREAM};

        for(int i = 0 ; i < numberOfCases ; i++) {
            assertThat(Menu.findMenu(strings[i])).isEqualTo(menus[i]);
        }
    }

    @DisplayName("메뉴 이름이 존재하지 않는다면 예외를 발생한다.")
    @ValueSource(strings = {"봉골레파스타", "할로윈파스타", "안심스테이크"})
    @ParameterizedTest
    void findMenuByWrongName(String input) {

        assertThatThrownBy(() -> Menu.findMenu(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
