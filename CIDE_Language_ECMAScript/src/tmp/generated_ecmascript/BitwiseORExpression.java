package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BitwiseORExpression extends GenASTNode {
  public BitwiseORExpression(BitwiseXORExpression bitwiseXORExpression, ArrayList<BitwiseXORExpression> bitwiseXORExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BitwiseXORExpression>("bitwiseXORExpression", bitwiseXORExpression),
      new PropertyZeroOrMore<BitwiseXORExpression>("bitwiseXORExpression1", bitwiseXORExpression1)
    }, firstToken, lastToken);
  }
  public BitwiseORExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BitwiseORExpression(cloneProperties(),firstToken,lastToken);
  }
  public BitwiseXORExpression getBitwiseXORExpression() {
    return ((PropertyOne<BitwiseXORExpression>)getProperty("bitwiseXORExpression")).getValue();
  }
  public ArrayList<BitwiseXORExpression> getBitwiseXORExpression1() {
    return ((PropertyZeroOrMore<BitwiseXORExpression>)getProperty("bitwiseXORExpression1")).getValue();
  }
}
