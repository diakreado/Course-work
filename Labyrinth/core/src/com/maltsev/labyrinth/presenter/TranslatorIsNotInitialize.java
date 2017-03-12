package com.maltsev.labyrinth.presenter;


/**
 * Исключение бросается, если Translator не инициалилизировали, т.е. не передали информацию о размере блока
 */
class TranslatorIsNotInitialize extends RuntimeException {

    TranslatorIsNotInitialize() {

        super("Нельзя пользоватся ПереводчикомКоординат без его инициализации");
    }
}
