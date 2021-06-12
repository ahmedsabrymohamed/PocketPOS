package com.pocket.pos.projections;

import com.pocket.pos.model.BillSecondParty;

public interface BillSecondPartyOnlyProjection extends BillProjection {
	BillSecondParty getSecondParty();
}
