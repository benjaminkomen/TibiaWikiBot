package com.wikia.tibia.objects;

import com.wikia.tibia.enums.Article;
import com.wikia.tibia.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WikiObject {

    private String name;
    private Article article;
    private String actualname;
    private String plural;
    private String implemented;
    private String notes;
    private String history;
    private Status status;

    public boolean isDeprecatedOrEvent() {
        return (Status.DEPRECATED.equals(status) || Status.EVENT.equals(status));
    }
}