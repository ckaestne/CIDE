package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class classdecl extends definition {
  public classdecl(context context2, naam naam1, ArrayList<var> var, whereDecls whereDecls, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<context>("context2", context2),
      new PropertyOne<naam>("naam1", naam1),
      new PropertyOneOrMore<var>("var", var),
      new PropertyZeroOrOne<whereDecls>("whereDecls", whereDecls)
    }, firstToken, lastToken);
  }
  public classdecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new classdecl(cloneProperties(),firstToken,lastToken);
  }
  public context getContext2() {
    return ((PropertyZeroOrOne<context>)getProperty("context2")).getValue();
  }
  public naam getNaam1() {
    return ((PropertyOne<naam>)getProperty("naam1")).getValue();
  }
  public ArrayList<var> getVar() {
    return ((PropertyOneOrMore<var>)getProperty("var")).getValue();
  }
  public whereDecls getWhereDecls() {
    return ((PropertyZeroOrOne<whereDecls>)getProperty("whereDecls")).getValue();
  }
}
