package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class declaration2 extends declaration {
  public declaration2(function function1, ArrayList<altExprAss> altExprAss, whereDecls whereDecls1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<function>("function1", function1),
      new PropertyOneOrMore<altExprAss>("altExprAss", altExprAss),
      new PropertyZeroOrOne<whereDecls>("whereDecls1", whereDecls1)
    }, firstToken, lastToken);
  }
  public declaration2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new declaration2(cloneProperties(),firstToken,lastToken);
  }
  public function getFunction1() {
    return ((PropertyOne<function>)getProperty("function1")).getValue();
  }
  public ArrayList<altExprAss> getAltExprAss() {
    return ((PropertyOneOrMore<altExprAss>)getProperty("altExprAss")).getValue();
  }
  public whereDecls getWhereDecls1() {
    return ((PropertyZeroOrOne<whereDecls>)getProperty("whereDecls1")).getValue();
  }
}
