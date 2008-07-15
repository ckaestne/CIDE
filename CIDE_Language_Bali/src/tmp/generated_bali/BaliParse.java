package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BaliParse extends GenASTNode implements ISourceFile {
  public BaliParse(OptionsNode optionsNode, ParserCode parserCode, Statements statements, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<OptionsNode>("optionsNode", optionsNode),
      new PropertyZeroOrOne<ParserCode>("parserCode", parserCode),
      new PropertyZeroOrOne<Statements>("statements", statements)
    }, firstToken, lastToken);
  }
  public BaliParse(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BaliParse(cloneProperties(),firstToken,lastToken);
  }
  public OptionsNode getOptionsNode() {
    return ((PropertyZeroOrOne<OptionsNode>)getProperty("optionsNode")).getValue();
  }
  public ParserCode getParserCode() {
    return ((PropertyZeroOrOne<ParserCode>)getProperty("parserCode")).getValue();
  }
  public Statements getStatements() {
    return ((PropertyZeroOrOne<Statements>)getProperty("statements")).getValue();
  }
}
