package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CaseBlock extends GenASTNode {
  public CaseBlock(CaseClauses caseClauses, CaseBlockEnd caseBlockEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<CaseClauses>("caseClauses", caseClauses),
      new PropertyOne<CaseBlockEnd>("caseBlockEnd", caseBlockEnd)
    }, firstToken, lastToken);
  }
  public CaseBlock(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CaseBlock(cloneProperties(),firstToken,lastToken);
  }
  public CaseClauses getCaseClauses() {
    return ((PropertyZeroOrOne<CaseClauses>)getProperty("caseClauses")).getValue();
  }
  public CaseBlockEnd getCaseBlockEnd() {
    return ((PropertyOne<CaseBlockEnd>)getProperty("caseBlockEnd")).getValue();
  }
}
