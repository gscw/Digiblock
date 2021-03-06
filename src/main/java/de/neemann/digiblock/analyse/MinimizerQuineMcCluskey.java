/*
 * Copyright (c) 2017 Helmut Neemann
 * Use of this source code is governed by the GPL v3 license
 * that can be found in the LICENSE file.
 */
package de.neemann.digiblock.analyse;

import de.neemann.digiblock.analyse.expression.Expression;
import de.neemann.digiblock.analyse.expression.ExpressionException;
import de.neemann.digiblock.analyse.expression.Variable;
import de.neemann.digiblock.analyse.expression.format.FormatterException;
import de.neemann.digiblock.analyse.quinemc.BoolTable;
import de.neemann.digiblock.analyse.quinemc.QuineMcCluskey;
import de.neemann.digiblock.analyse.quinemc.TableRow;
import de.neemann.digiblock.analyse.quinemc.primeselector.PrimeSelector;
import de.neemann.digiblock.analyse.quinemc.primeselector.PrimeSelectorDefault;
import de.neemann.digiblock.gui.components.table.ExpressionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The normal QMC minimizer.
 */
public class MinimizerQuineMcCluskey implements MinimizerInterface {
    @Override
    public void minimize(List<Variable> vars, BoolTable boolTable, String resultName, ExpressionListener listener) throws ExpressionException, FormatterException {
        QuineMcCluskey qmc = createQuineMcCluskey(vars)
                .fillTableWith(boolTable);
        PrimeSelector ps = new PrimeSelectorDefault();
        Expression e = qmc.simplify(ps).getExpression();

        if (ps.getAllSolutions() != null) {
            for (ArrayList<TableRow> i : ps.getAllSolutions()) {
                listener.resultFound(resultName, QuineMcCluskey.addAnd(null, i, vars));
            }
        } else {
            listener.resultFound(resultName, e);
        }
    }

    /**
     * Creates a specific QMC algorithm
     *
     * @param vars the variables to use
     * @return the QMC instance
     */
    protected QuineMcCluskey createQuineMcCluskey(List<Variable> vars) {
        return new QuineMcCluskey(vars);
    }
}
