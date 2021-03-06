package ch.xwr.seicentobilling.business.auth;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.nimbusds.jwt.JWTClaimsSet;
import com.xdev.security.authorization.Role;
import com.xdev.security.authorization.Subject;
import com.xdev.server.aa.openid.auth.AzureUser;

import ch.xwr.seicentobilling.entities.CostAccount;

public class SeicentoUser implements Subject, Serializable {
	private AzureUser aadUser = null;
	private CostAccount cst = null;

	private String name = "";
	//private final String uid = "";
	//private final String token = "";
	private volatile Set<Role> roles;

	public SeicentoUser() {
		this("unknown");
    }

	public SeicentoUser(final String username) {
		this.name = username;
		initRoles();
    }

	private void initRoles() {
		this.roles = new HashSet<>();

		this.roles.add(Role.New("BillingAdmin"));
	}

	@Override
	public String name() {
		if (getAzureUser() != null) {
			return getAzureUser().name();
		}
		return this.name;
	}
	@Override
	public Set<Role> roles() {
		if (getAzureUser() != null) {
			return getAzureUser().roles();
		}
		return this.roles;
	}

	public JWTClaimsSet getClaimSet()
	{
		if (getAzureUser() != null) {
			return getAzureUser().getClaimSet();
		}

		final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
			     .subject(this.name)
			     .issueTime(new Date())
			     .issuer("XWare GmbH")
			     .build();

		return claimsSet;
	}

	public AzureUser getAzureUser() {
		return this.aadUser;
	}
	public void setAzureUser(final AzureUser currentUser) {
		this.aadUser = currentUser;
	}

	public CostAccount getAssignedAccount() {
		return this.cst;
	}

	public void setAssignedAccount(final CostAccount cst) {
		this.cst = cst;
	}

}
