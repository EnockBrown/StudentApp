package com.example.studentapp.models;

import java.util.List;

public class UnitsResponse {
    private boolean error;
    private List<Units> units;

    public UnitsResponse(boolean error, List<Units> units) {
        this.error = error;
        this.units = units;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Units> getUnits() {
        return units;
    }

    public void setUnits(List<Units> units) {
        this.units = units;
    }
}
