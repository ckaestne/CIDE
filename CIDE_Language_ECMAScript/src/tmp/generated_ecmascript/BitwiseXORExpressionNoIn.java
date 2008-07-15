package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BitwiseXORExpressionNoIn extends GenASTNode {
  public BitwiseXORExpressionNoIn(BitwiseANDExpressionNoIn bitwiseANDExpressionNoIn, ArrayList<BitwiseANDExpressionNoIn> bitwiseANDExpressionNoIn1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BitwiseANDExpressionNoIn>("bitwiseANDExpressionNoIn", bitwiseANDExpressionNoIn),
      new PropertyZeroOrMore<BitwiseANDExpressionNoIn>("bitwiseANDExpressionNoIn1", bitwiseANDExpressionNoIn1)
    }, firstToken, lastToken);
  }
  public BitwiseXORExpressionNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BitwiseXORExpressionNoIn(cloneProperties(),firstToken,lastToken);
  }
  public BitwiseANDExpressionNoIn getBitwiseANDExpressionNoIn() {
    return ((PropertyOne<BitwiseANDExpressionNoIn>)getProperty("bitwiseANDExpressionNoIn")).getValue();
  }
  public ArrayList<BitwiseANDExpressionNoIn> getBitwiseANDExpressionNoIn1() {
    return ((PropertyZeroOrMore<BitwiseANDExpressionNoIn>)getProperty("bitwiseANDExpressionNoIn1")).getValue();
  }
}
