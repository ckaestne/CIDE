package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultAndText extends GenASTNode {
  public MultAndText(Mult mult, ArrayList<Text> text, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Mult>("mult", mult),
      new PropertyZeroOrMore<Text>("text", text)
    }, firstToken, lastToken);
  }
  public MultAndText(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MultAndText(cloneProperties(),firstToken,lastToken);
  }
  public Mult getMult() {
    return ((PropertyOne<Mult>)getProperty("mult")).getValue();
  }
  public ArrayList<Text> getText() {
    return ((PropertyZeroOrMore<Text>)getProperty("text")).getValue();
  }
}
