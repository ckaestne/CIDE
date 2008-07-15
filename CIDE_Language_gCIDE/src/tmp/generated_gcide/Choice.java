package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Choice extends GenASTNode {
  public Choice(ArrayList<Text> text, ArrayList<MultAndText> multAndText, ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Text>("text", text),
      new PropertyOneOrMore<MultAndText>("multAndText", multAndText),
      new PropertyZeroOrOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public Choice(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Choice(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Text> getText() {
    return ((PropertyZeroOrMore<Text>)getProperty("text")).getValue();
  }
  public ArrayList<MultAndText> getMultAndText() {
    return ((PropertyOneOrMore<MultAndText>)getProperty("multAndText")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
