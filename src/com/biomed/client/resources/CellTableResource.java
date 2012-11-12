package com.biomed.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;

public interface CellTableResource extends Resources {

        public CellTable.Resources INSTANCE =
                GWT.create(CellTableResource.class);

        /**
         * The styles used in this widget.
         */
        @Source("CellTableResource.css")
        CellTable.Style cellTableStyle();
}