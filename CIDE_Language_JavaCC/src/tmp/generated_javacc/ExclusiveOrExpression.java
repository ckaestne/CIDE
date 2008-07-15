package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExclusiveOrExpression extends GenASTNode {
  public ExclusiveOrExpression(AndExpression andExpression, ArrayList<AndExpression> andExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AndExpression>("andExpression", andExpression),
      new PropertyZeroOrMore<AndExpression>("andExpression1", andExpression1)
    }, firstToken, lastToken);
  }
  public ExclusiveOrExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExclusiveOrExpression(cloneProperties(),firstToken,lastToken);
  }
  public AndExpression getAndExpression() {
    return ((PropertyOne<AndExpression>)getProperty("andExpression")).getValue();
  }
  public ArrayList<AndExpression> getAndExpression1() {
    return ((PropertyZeroOrMore<AndExpression>)getProperty("andExpression1")).getValue();
  }
}
