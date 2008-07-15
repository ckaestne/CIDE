package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Block extends GenASTNode {
  public Block(ArrayList<Statement> statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public Block(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Block(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Statement> getStatement() {
    return ((PropertyZeroOrMore<Statement>)getProperty("statement")).getValue();
  }
}
