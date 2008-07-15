package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CaseBlockEnd2 extends CaseBlockEnd {
  public CaseBlockEnd2(DefaultClause defaultClause, CaseClauses caseClauses, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<DefaultClause>("defaultClause", defaultClause),
      new PropertyZeroOrOne<CaseClauses>("caseClauses", caseClauses)
    }, firstToken, lastToken);
  }
  public CaseBlockEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CaseBlockEnd2(cloneProperties(),firstToken,lastToken);
  }
  public DefaultClause getDefaultClause() {
    return ((PropertyOne<DefaultClause>)getProperty("defaultClause")).getValue();
  }
  public CaseClauses getCaseClauses() {
    return ((PropertyZeroOrOne<CaseClauses>)getProperty("caseClauses")).getValue();
  }
}
