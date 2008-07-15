package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BitwiseXORExpression extends GenASTNode {
  public BitwiseXORExpression(BitwiseANDExpression bitwiseANDExpression, ArrayList<BitwiseANDExpression> bitwiseANDExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BitwiseANDExpression>("bitwiseANDExpression", bitwiseANDExpression),
      new PropertyZeroOrMore<BitwiseANDExpression>("bitwiseANDExpression1", bitwiseANDExpression1)
    }, firstToken, lastToken);
  }
  public BitwiseXORExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BitwiseXORExpression(cloneProperties(),firstToken,lastToken);
  }
  public BitwiseANDExpression getBitwiseANDExpression() {
    return ((PropertyOne<BitwiseANDExpression>)getProperty("bitwiseANDExpression")).getValue();
  }
  public ArrayList<BitwiseANDExpression> getBitwiseANDExpression1() {
    return ((PropertyZeroOrMore<BitwiseANDExpression>)getProperty("bitwiseANDExpression1")).getValue();
  }
}
