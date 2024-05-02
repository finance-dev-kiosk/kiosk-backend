package finance.dev.domain.type;

import lombok.Getter;

@Getter
public enum PeriodTime {
    OPENING("운영시간"),
    BREAKTIME("휴식시간");

    PeriodTime(String value) {}

    public String getValue() {
        return this.name();
    }
}
