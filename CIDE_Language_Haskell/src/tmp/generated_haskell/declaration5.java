package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class declaration5 extends declaration {
  public declaration5(patr patr1, ArrayList<altExprAss> altExprAss1, whereDecls whereDecls3, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<patr>("patr1", patr1),
      new PropertyOneOrMore<altExprAss>("altExprAss1", altExprAss1),
      new PropertyZeroOrOne<whereDecls>("whereDecls3", whereDecls3)
    }, firstToken, lastToken);
  }
  public declaration5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new declaration5(cloneProperties(),firstToken,lastToken);
  }
  public patr getPatr1() {
    return ((PropertyOne<patr>)getProperty("patr1")).getValue();
  }
  public ArrayList<altExprAss> getAltExprAss1() {
    return ((PropertyOneOrMore<altExprAss>)getProperty("altExprAss1")).getValue();
  }
  public whereDecls getWhereDecls3() {
    return ((PropertyZeroOrOne<whereDecls>)getProperty("whereDecls3")).getValue();
  }
}
