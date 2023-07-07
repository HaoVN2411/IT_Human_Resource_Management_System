/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package holiday;

import java.time.LocalDate;

/**
 *
 * @author flami
 */
public class HolidayDTO {
    private LocalDate date;
    private String note;

    public HolidayDTO(LocalDate date, String note) {
        this.date = date;
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
