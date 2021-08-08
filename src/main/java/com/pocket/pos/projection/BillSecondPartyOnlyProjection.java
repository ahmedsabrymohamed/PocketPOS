package com.pocket.pos.projection;

import com.pocket.pos.entity.BillSecondParty;

public interface BillSecondPartyOnlyProjection extends BillProjection {
	BillSecondParty getSecondParty();
}
