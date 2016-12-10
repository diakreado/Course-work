package com.maltsev.labyrinth.model.field;

/**
 *  Выход за границу поля - исключение
 */
public class OutOfBoundaryOfTheField extends Exception{

    /**
     *  Имя параметра, который не соответствует норме
     */
    private String nameOfParam;

    /**
     *  Значение параметра, которое привело к исключению
     */
    private int valueOfParam;

    /**
     *  Максимально допустимое значение параметра
     */
    private int maximumAllowableValueOfParam;

    /**
     * Имя класса, который бросил исключение
     */
    private String infoAboutPlaceFromThrowingException;

    /**
     * Конструктор, в который передаётся информация об ошибке
     * @param infoAboutPlaceFromThrowingException имя класса, который бросил исключение
     * @param nameOfParam имя параметра, который не соответствует норме
     * @param valueOfParam значение параметра, которое привело к исключению
     * @param maximumAllowableValueOfParam максимально допустимое значение параметра
     */
    public OutOfBoundaryOfTheField(final String infoAboutPlaceFromThrowingException,final String nameOfParam,
                                   final int valueOfParam, final int maximumAllowableValueOfParam) {

        super("\n\n" + infoAboutPlaceFromThrowingException + " :" +
                "\nНеправильно задан " + nameOfParam + " : " + valueOfParam +
                "\nДопустимое значение : [0;" + maximumAllowableValueOfParam + "]");

        this.nameOfParam = nameOfParam;
        this.valueOfParam = valueOfParam;
        this.maximumAllowableValueOfParam = maximumAllowableValueOfParam;
        this.infoAboutPlaceFromThrowingException = infoAboutPlaceFromThrowingException;
    }

    /**
     * @return имя параметра, который не соответствует норме
     */
    public String getNameOfParam() {

        return nameOfParam;
    }

    /**
     * @return значение параметра, которое привело к исключению
     */
    public int getValueOfParam() {

        return valueOfParam;
    }

    /**
     * @return максимально допустимое значение параметра
     */
    public int getMaximumAllowableValueOfParam() {

        return maximumAllowableValueOfParam;
    }

    /**
     * @return откуда исключение прилетело
     */
    public String getInfoAboutPlaceFromThrowingException() {

        return infoAboutPlaceFromThrowingException;
    }
}
