package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expansion_unit5 extends expansion_unit {
  public expansion_unit5(PrimaryExpression primaryExpression, expansion_unitMain expansion_unitMain, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<PrimaryExpression>("primaryExpression", primaryExpression),
      new PropertyOne<expansion_unitMain>("expansion_unitMain", expansion_unitMain)
    }, firstToken, lastToken);
  }
  public expansion_unit5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expansion_unit5(cloneProperties(),firstToken,lastToken);
  }
  public PrimaryExpression getPrimaryExpression() {
    return ((PropertyZeroOrOne<PrimaryExpression>)getProperty("primaryExpression")).getValue();
  }
  public expansion_unitMain getExpansion_unitMain() {
    return ((PropertyOne<expansion_unitMain>)getProperty("expansion_unitMain")).getValue();
  }
}
