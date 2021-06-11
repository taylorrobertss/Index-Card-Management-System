/*******************************************************************************
 * Copyright (C) 2021 dapeng
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 ******************************************************************************/
package org.dapeng.usicms.handler;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public abstract class StringTransferHandler extends TransferHandler {
	private static final long serialVersionUID = 1L;

	protected abstract String exportString(JComponent c);

	protected abstract void importString(JComponent c, String str);

	protected abstract void cleanup(JComponent c, boolean remove);

	protected Transferable createTransferable(JComponent c) {
		return new StringSelection(exportString(c));
	}

	public int getSourceActions(JComponent c) {
		return COPY_OR_MOVE;
	}

	public boolean importData(JComponent c, Transferable t) {
		if (canImport(c, t.getTransferDataFlavors())) {
			try {
				String str = (String) t.getTransferData(DataFlavor.stringFlavor);
				importString(c, str);
				return true;
			} catch (UnsupportedFlavorException ufe) {
			} catch (IOException ioe) {
			}
		}

		return false;
	}

	protected void exportDone(JComponent c, Transferable data, int action) {
		cleanup(c, action == MOVE);
	}

	public boolean canImport(JComponent c, DataFlavor[] flavors) {
		for (int i = 0; i < flavors.length; i++) {
			if (DataFlavor.stringFlavor.equals(flavors[i])) {
				return true;
			}
		}
		return false;
	}
}
