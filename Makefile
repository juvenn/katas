
python.test:
	PYTHONPATH=src python3 -m unittest discover -s test/katas

.PHONY: python.test
