package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BitwiseORExpressionNoIn extends GenASTNode {
  public BitwiseORExpressionNoIn(BitwiseXORExpressionNoIn bitwiseXORExpressionNoIn, ArrayList<BitwiseXORExpressionNoIn> bitwiseXORExpressionNoIn1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BitwiseXORExpressionNoIn>("bitwiseXORExpressionNoIn", bitwiseXORExpressionNoIn),
      new PropertyZeroOrMore<BitwiseXORExpressionNoIn>("bitwiseXORExpressionNoIn1", bitwiseXORExpressionNoIn1)
    }, firstToken, lastToken);
  }
  public BitwiseORExpressionNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BitwiseORExpressionNoIn(cloneProperties(),firstToken,lastToken);
  }
  public BitwiseXORExpressionNoIn getBitwiseXORExpressionNoIn() {
    return ((PropertyOne<BitwiseXORExpressionNoIn>)getProperty("bitwiseXORExpressionNoIn")).getValue();
  }
  public ArrayList<BitwiseXORExpressionNoIn> getBitwiseXORExpressionNoIn1() {
    return ((PropertyZeroOrMore<BitwiseXORExpressionNoIn>)getProperty("bitwiseXORExpressionNoIn1")).getValue();
  }
}
