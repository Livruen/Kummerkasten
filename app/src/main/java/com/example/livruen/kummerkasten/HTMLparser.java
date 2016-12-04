package com.example.livruen.kummerkasten;

public enum HTMLparser {
    HEADER("document.getElementsByTagName('header')[0].style.display=\"none\";"),
    ENTRY_HEADER("document.getElementsByClassName('entry-header')[0].style.display=\"none\";"),
    HEADER_IMAGE("document.getElementsByClassName('header-image')[0].style.display=\"none\";"),
    PANEL("document.getElementsByClassName('panel-row-style')[0].style.display=\"none\";"),
    FOOTER("document.getElementsByTagName('footer-main')[0].style.display=\"none\";"),
    COLOFON("document.getElementsByClassName('site-footer')[0].style.display=\"none\"  ;"),
   CONTENT("document.getElementsByClassName('panel-row-style')[0].style.display=\"none\";");

    private String value;

    public String getValue() {
        return value;
    }

    HTMLparser(String value) {
        this.value = value;
    }
}