package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Term extends GenASTNode {
  public Term(PrimaryExpression primaryExpression, ArrayList<TimesOrDivide> timesOrDivide, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimaryExpression>("primaryExpression", primaryExpression),
      new PropertyZeroOrMore<TimesOrDivide>("timesOrDivide", timesOrDivide)
    }, firstToken, lastToken);
  }
  public Term(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Term(cloneProperties(),firstToken,lastToken);
  }
  public PrimaryExpression getPrimaryExpression() {
    return ((PropertyOne<PrimaryExpression>)getProperty("primaryExpression")).getValue();
  }
  public ArrayList<TimesOrDivide> getTimesOrDivide() {
    return ((PropertyZeroOrMore<TimesOrDivide>)getProperty("timesOrDivide")).getValue();
  }
}
