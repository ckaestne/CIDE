package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class OneOrZero extends GenASTNode {
  public OneOrZero(ArrayList<Text> text, Unit unit, ArrayList<Text> text1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Text>("text", text),
      new PropertyOne<Unit>("unit", unit),
      new PropertyZeroOrMore<Text>("text1", text1)
    }, firstToken, lastToken);
  }
  public OneOrZero(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new OneOrZero(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Text> getText() {
    return ((PropertyZeroOrMore<Text>)getProperty("text")).getValue();
  }
  public Unit getUnit() {
    return ((PropertyOne<Unit>)getProperty("unit")).getValue();
  }
  public ArrayList<Text> getText1() {
    return ((PropertyZeroOrMore<Text>)getProperty("text1")).getValue();
  }
}
