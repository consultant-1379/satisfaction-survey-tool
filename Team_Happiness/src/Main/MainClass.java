package Main;

import Empowered.EmpoweredUpdate;
import Home.homeUpdate;
import Mastery.MasteryUpdate;
import Mission.MissionUpdate;
import Operator.Constants;
import Organization.OrganizationUpdate;

public class MainClass 
{
	public static void main(String[] args)
	{
		MissionUpdate AXISmissionUpdate = new MissionUpdate();
		AXISmissionUpdate.init("select avg(mission) from team_happiness where DATE >= DATE_ADD(NOW(), INTERVAL -21 DAY) and team like '%Axis%' union all select avg(mission) from team_happiness where team like '%Axis%';",Constants.AXISMISSIONURL,Constants.AXISMISSIONJSON);
		MissionUpdate STRATUSmissionUpdate = new MissionUpdate();
		STRATUSmissionUpdate.init("select avg(mission) from team_happiness where DATE >= DATE_ADD(NOW(), INTERVAL -21 DAY) and team like '%Stratus%' union all select avg(mission) from team_happiness where team like '%Stratus%';", Constants.STRATUSMISSIONURL,Constants.STRATUSMISSIONJSON);
		
		MasteryUpdate AXISmasteryUpdate = new MasteryUpdate();
		AXISmasteryUpdate.init("select avg(mastery) from team_happiness where DATE >= DATE_ADD(NOW(), INTERVAL -21 DAY) and team like '%Axis%' union all select avg(mastery) from team_happiness where team like '%Axis%';",Constants.AXISMASTERYURL,Constants.AXISMASTERYJSON);
		MasteryUpdate STRATUSmasteryUpdate = new MasteryUpdate();
		STRATUSmasteryUpdate.init("select avg(mastery) from team_happiness where DATE >= DATE_ADD(NOW(), INTERVAL -21 DAY) and team like '%Stratus%' union all select avg(mastery) from team_happiness where team like '%Stratus%';",Constants.STRATUSMASTERYURL,Constants.STRATUSMASTERYJSON);
		
		OrganizationUpdate AXISorganizationUpdate = new OrganizationUpdate();
		AXISorganizationUpdate.init("select avg(organization) from team_happiness where DATE >= DATE_ADD(NOW(), INTERVAL -21 DAY) and team like '%Axis%' union all select avg(organization) from team_happiness where team like '%Axis%';", Constants.AXISORGANIZATIONURL,Constants.AXISORGANIZATIONJSON);
		OrganizationUpdate STRATUSorganizationUpdate = new OrganizationUpdate();
		STRATUSorganizationUpdate.init("select avg(organization) from team_happiness where DATE >= DATE_ADD(NOW(), INTERVAL -21 DAY) and team like '%Stratus%' union all select avg(organization) from team_happiness where team like '%Stratus%';" , Constants.STRATUSORGANIZATIONURL,Constants.STRATUSORGANIZATIONJSON);
		
	    EmpoweredUpdate STRATUSempoweredUpdate = new EmpoweredUpdate();
		STRATUSempoweredUpdate.init("select avg(empowered) from team_happiness where DATE >= DATE_ADD(NOW(), INTERVAL -21 DAY) and team like '%Stratus%' union all select avg(empowered) from team_happiness where team like '%Stratus%';",Constants.STRATUSEMPOWEREDURL,Constants.STRATUSEMPOWEREDJSON);
		EmpoweredUpdate AXISempoweredUpdate = new EmpoweredUpdate();
		AXISempoweredUpdate.init("select avg(empowered) from team_happiness where DATE >= DATE_ADD(NOW(), INTERVAL -21 DAY) and team like '%Axis%' union all select avg(empowered) from team_happiness where team like '%Axis%';", Constants.AXISEMPOWEREDURL, Constants.AXISEMPOWEREDJSON);
		
		homeUpdate AXIShomeUpdate = new homeUpdate();
		AXIShomeUpdate.init("select (avg(mission)+ avg(mastery)+ avg(organization)+ avg(empowered))/4 from team_happiness as average where date >= DATE_ADD(NOW(), INTERVAL -21 DAY) and team like '%Axis%' union all select (avg(mission)+ avg(mastery)+ avg(organization)+ avg(empowered))/4 from team_happiness as average where team like '%Axis%' ;", Constants.AXISHOMEURL, Constants.AXISHOMEJSON);
		homeUpdate STRATUShomeUpdate = new homeUpdate();
		STRATUShomeUpdate.init("select (avg(mission)+ avg(mastery)+ avg(organization)+ avg(empowered))/4 from team_happiness as average where date >= DATE_ADD(NOW(), INTERVAL -21 DAY) and team like '%Stratus%' union all select (avg(mission)+ avg(mastery)+ avg(organization)+ avg(empowered))/4 from team_happiness as average where team like '%Stratus%' ;", Constants.STRATUSHOMEURL, Constants.STRATUSHOMEJSON);
	}
}
