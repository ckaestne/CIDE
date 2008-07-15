package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CaseClauses extends GenASTNode {
  public CaseClauses(ArrayList<CaseClause> caseClause, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<CaseClause>("caseClause", caseClause)
    }, firstToken, lastToken);
  }
  public CaseClauses(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CaseClauses(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<CaseClause> getCaseClause() {
    return ((PropertyOneOrMore<CaseClause>)getProperty("caseClause")).getValue();
  }
}
